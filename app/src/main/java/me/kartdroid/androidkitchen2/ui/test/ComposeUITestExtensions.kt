package me.kartdroid.androidkitchen2.ui.test

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import me.kartdroid.androidkitchen2.BuildConfig

/**
 * https://developer.android.com/jetpack/compose/testing#uiautomator-interop
 *
 * Maps Test Tag as Resource ID for the Compose Sub-Tree
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.mapTestTagAsResourceID() = if (BuildConfig.DEBUG) {
    semantics {
        testTagsAsResourceId = true
    }
} else {
    this
}

/**
 * helper method to add the tag provided as test tag to the component
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.addTestTag(tag: String) = if ((BuildConfig.DEBUG) && tag.isNotBlank()) {
    semantics {
        testTagsAsResourceId = true
        testTag = tag
        testTag(tag)
    }
} else {
    this
}
