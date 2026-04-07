from playwright.sync_api import sync_playwright
import time

def verify_fluid_shockwave():
    print("Starting Playwright verification for Fluid Shockwave...")
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page(
            viewport={'width': 1280, 'height': 800},
            device_scale_factor=2,
            color_scheme='dark' # Ensure dark mode for best contrast
        )

        page.goto('http://localhost:3000')
        print("Navigated to localhost:3000")

        # Wait for the main wrapper instead
        page.wait_for_selector('body', timeout=10000)
        print("Body loaded")
        time.sleep(2) # Give React time to mount

        # In order to force the awakening state for testing, we can expose a global variable in the code,
        # or we can click around. The app usually has a chat interface.
        # Let's try to just render the component in isolation if clicking fails.
        # But first let's see what it looks like normally.

        # Let's execute some JS to force the awakening state if it uses a store.
        # Zustand stores often attach to window if we configured it, but let's try finding the character image first.
        try:
             # Find image that looks like the character
             page.click('img.rounded-full', timeout=5000)
             print("Clicked character image")
             time.sleep(1)
        except:
             print("Could not click image.")

        # Take screenshot of the whole page
        page.screenshot(path='verify-fluid-shockwave.png')

        # Let's also create a test route to view ONLY the shockwave to be absolutely sure.
        browser.close()

if __name__ == "__main__":
    verify_fluid_shockwave()
