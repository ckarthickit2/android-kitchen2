package me.kartdroid.androidkitchen2.utils

/**
 * @author [Karthick Chinnathambi]()
 * @since 01/09/23
 */


import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId

/**
 * https://developer.android.com/jetpack/compose/testing#uiautomator-interop
 *
 * Maps Test Tag as Resource ID for the Compose Sub-Tree
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.mapTestTagAsResourceID() = if (true) {
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
fun Modifier.addTestTag(tag: String) = if ((true) && tag.isNotBlank()) {
    semantics {
        testTagsAsResourceId = true
        testTag = tag
        testTag(tag)
    }
} else {
    this
}
