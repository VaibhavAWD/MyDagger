package practice.mydagger.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit tests for implementation of [SharedPreferencesStorage].
 */
@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesStorageTest {

    // SUT
    private lateinit var mockSharedPreferencesStorage: SharedPreferencesStorage
    private lateinit var brokenMockSharedPreferencesStorage: SharedPreferencesStorage

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var brokenMockContext: Context

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var brokenMockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    @Mock
    private lateinit var brokenMockEditor: SharedPreferences.Editor

    // test data
    private val testKey = "test-key"
    private val testValue = "test-value"

    @Before
    fun setUp() {
        mockSharedPreferencesStorage = createMockSharedPreferencesStorage()
        brokenMockSharedPreferencesStorage = createBrokenMockSharedPreferencesStorage()
    }

    @Test
    fun `saving value saves it in the shared preferences`() {
        // WHEN - saving a value
        mockSharedPreferencesStorage.setString(testKey, testValue)

        // THEN - verify that the value is saved
        val value = mockSharedPreferencesStorage.getString(testKey)
        assertThat(value).isEqualTo(testValue)
    }

    @Test
    fun `saving value failure does not saves the value`() {
        // WHEN - saving a value
        brokenMockSharedPreferencesStorage.setString(testKey, testValue)

        // THEN - verify that the value is not saved
        val value = brokenMockSharedPreferencesStorage.getString(testKey)
        assertThat(value).isEmpty()
    }

    private fun createMockSharedPreferencesStorage(): SharedPreferencesStorage {
        `when`(mockContext.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(mockSharedPreferences)

        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)

        `when`(mockSharedPreferences.getString(anyString(), anyString()))
            .thenReturn(testValue)

        return SharedPreferencesStorage(mockContext)
    }

    private fun createBrokenMockSharedPreferencesStorage(): SharedPreferencesStorage {
        `when`(brokenMockContext.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(brokenMockSharedPreferences)

        `when`(brokenMockSharedPreferences.edit()).thenReturn(brokenMockEditor)

        `when`(brokenMockSharedPreferences.getString(anyString(), anyString()))
            .thenReturn("")

        return SharedPreferencesStorage(brokenMockContext)
    }
}