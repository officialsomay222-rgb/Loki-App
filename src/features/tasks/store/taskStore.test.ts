import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { useTaskStore } from './taskStore';
import { db } from '@/src/db';
import { toast } from 'sonner';

// Mock sonner
vi.mock('sonner', () => ({
  toast: {
    success: vi.fn(),
    error: vi.fn(),
  }
}));

// Mock db
vi.mock('@/src/db', () => ({
  db: {
    tasks: {
      add: vi.fn(),
      orderBy: vi.fn().mockReturnThis(),
      reverse: vi.fn().mockReturnThis(),
      toArray: vi.fn().mockResolvedValue([]),
      update: vi.fn(),
      delete: vi.fn(),
    }
  }
}));

describe('useTaskStore', () => {
  beforeEach(() => {
    vi.clearAllMocks();
    useTaskStore.setState({ tasks: [], isLoading: true, error: null });

    // Mock crypto.randomUUID
    vi.stubGlobal('crypto', {
      ...global.crypto,
      randomUUID: () => 'test-uuid-1234'
    });
  });

  afterEach(() => {
    vi.unstubAllGlobals();
    vi.restoreAllMocks();
  });

  describe('addTask', () => {
    it('should successfully add a task', async () => {
      vi.spyOn(Date, 'now').mockReturnValue(1234567890);

      const title = 'Test Task';
      await useTaskStore.getState().addTask(title);

      // Check if db.tasks.add was called
      expect(db.tasks.add).toHaveBeenCalledWith({
        id: 'test-uuid-1234',
        title: 'Test Task',
        completed: false,
        createdAt: 1234567890
      });

      // Check Zustand state update
      const state = useTaskStore.getState();
      expect(state.tasks).toHaveLength(1);
      expect(state.tasks[0]).toEqual({
        id: 'test-uuid-1234',
        title: 'Test Task',
        completed: false,
        createdAt: 1234567890
      });

      // Check toast
      expect(toast.success).toHaveBeenCalledWith('Task added securely to local storage.');
      expect(toast.error).not.toHaveBeenCalled();
    });

    it('should handle errors when adding a task fails', async () => {
      // Mock db.tasks.add to throw an error
      const mockError = new Error('Failed to add to IndexedDB');
      vi.mocked(db.tasks.add).mockRejectedValueOnce(mockError);

      const consoleSpy = vi.spyOn(console, 'error').mockImplementation(() => {});

      const title = 'Test Task';
      await useTaskStore.getState().addTask(title);

      // Verify db call was made
      expect(db.tasks.add).toHaveBeenCalled();

      // Check Zustand state (should not be updated)
      const state = useTaskStore.getState();
      expect(state.tasks).toHaveLength(0);

      // Verify console.error was called
      expect(consoleSpy).toHaveBeenCalledWith("Failed to add task:", mockError);

      // Verify toast.error was called
      expect(toast.error).toHaveBeenCalledWith('Could not save the task.');
      expect(toast.success).not.toHaveBeenCalled();
    });
  });
});
