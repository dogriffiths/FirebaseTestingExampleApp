/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.notepad;

import android.Manifest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;
//import androidx.test.runner.screenshot.Screenshot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NotePadTest {

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Rule
    public ActivityTestRule<NotesList> activityRule = new ActivityTestRule<>(NotesList.class);

    @Test
    public void testAddNote() throws IOException {
        // Take a screenshot when app becomes visible.
        onView(isRoot());
//        Screenshot.capture().process();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add note")).perform(click());
//        onView(allOf(withId(R.id.note), hasFocus())).perform(typeTextIntoFocusedView("Note 1"));
        onView(withId(R.id.note)).perform(replaceText("Note 1"));
        closeSoftKeyboard();
        pressBack();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add note")).perform(click());
//        onView(allOf(withId(R.id.note), hasFocus())).perform(typeTextIntoFocusedView("Note 2"));
        onView(withId(R.id.note)).perform(replaceText("Note 2"));
        closeSoftKeyboard();
        pressBack();

        onView(withText("Note 1")).check(matches(isDisplayed()));
        onView(withText("Note 2")).check(matches(isDisplayed()));

        // Take a screenshot of the activity at the end of the test.
//        Screenshot.capture(activityRule.getActivity()).process();

    }
}

