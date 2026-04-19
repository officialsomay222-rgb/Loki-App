package com.lokixprime.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lokixprime.data.db.AppDatabase
import com.lokixprime.data.db.entity.SettingsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsDao = AppDatabase.getDatabase(application).settingsDao()

    private val _settings = MutableStateFlow(SettingsEntity())
    val settings: StateFlow<SettingsEntity> = _settings.asStateFlow()

    init {
        viewModelScope.launch {
            settingsDao.getSettingsFlow().collectLatest { entity ->
                if (entity != null) {
                    _settings.value = entity
                } else {
                    // Create default settings if not exists
                    val defaultSettings = SettingsEntity()
                    settingsDao.saveSettings(defaultSettings)
                    _settings.value = defaultSettings
                }
            }
        }
    }

    fun updateSettings(newSettings: SettingsEntity) {
        viewModelScope.launch {
            settingsDao.saveSettings(newSettings)
        }
    }
}
