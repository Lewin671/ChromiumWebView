// Copyright (c) 2014 mogoweb. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.mogoweb.chrome.impl;

import android.os.StrictMode;
import android.util.AndroidRuntimeException;
import android.util.Log;

import org.chromium.content.R1;

import top.qingyingliu.webview.R;

/**
 * Top level factory, used creating all the main WebView implementation classes.
 *
 * @hide
 */
public final class WebViewFactory {

    private static final String CHROMIUM_WEBVIEW_FACTORY =
            "com.mogoweb.chrome.impl.WebViewChromiumFactoryProvider";

    private static final String LOGTAG = "WebViewFactory";

    private static final boolean DEBUG = true;

    private static class Preloader {
        static WebViewFactoryProvider sPreloadedProvider;
        static {
            try {
                sPreloadedProvider = getFactoryClass().newInstance();
            } catch (Exception e) {
                Log.w(LOGTAG, "error preloading provider", e);
            }

            R1.attr.select_dialog_multichoice = R.attr.select_dialog_multichoice;
            R1.attr.select_dialog_singlechoice = R.attr.select_dialog_singlechoice;
            R1.dimen.link_preview_overlay_radius = R.dimen.link_preview_overlay_radius;
            R1.drawable.ondemand_overlay = R.drawable.ondemand_overlay;
            R1.id.ampm = R.id.ampm;
            R1.id.arrow_image = R.id.arrow_image;
            R1.id.date_picker = R.id.date_picker;
            R1.id.date_time_suggestion_value = R.id.date_time_suggestion_value;
            R1.id.hour = R.id.hour;
            R1.id.main_text = R.id.main_text;
            R1.id.milli = R.id.milli;
            R1.id.minute = R.id.minute;
            R1.id.pickers = R.id.pickers;
            R1.id.position_in_year = R.id.position_in_year;
            R1.id.second = R.id.second;
            R1.id.second_colon = R.id.second_colon;
            R1.id.second_dot = R.id.second_dot;
            R1.id.select_action_menu_select_all = R.id.select_action_menu_select_all;
            R1.id.select_action_menu_cut = R.id.select_action_menu_cut;
            R1.id.select_action_menu_copy = R.id.select_action_menu_copy;
            R1.id.select_action_menu_paste = R.id.select_action_menu_paste;
            R1.id.select_action_menu_share = R.id.select_action_menu_share;
            R1.id.select_action_menu_web_search = R.id.select_action_menu_web_search;
            R1.id.sub_text = R.id.sub_text;
            R1.id.time_picker = R.id.time_picker;
            R1.id.year = R.id.year;
            R1.layout.date_time_picker_dialog = R.layout.date_time_picker_dialog;
            R1.layout.date_time_suggestion = R.layout.date_time_suggestion;
            R1.layout.two_field_date_picker = R.layout.two_field_date_picker;
            R1.layout.multi_field_time_picker_dialog = R.layout.multi_field_time_picker_dialog;
            R1.layout.validation_message_bubble = R.layout.validation_message_bubble;
            R1.menu.select_action_menu = R.menu.select_action_menu;
            R1.string.accessibility_content_view = R.string.accessibility_content_view;
            R1.string.accessibility_date_picker_month = R.string.accessibility_date_picker_month;
            R1.string.accessibility_date_picker_week = R.string.accessibility_date_picker_week;
            R1.string.accessibility_date_picker_year = R.string.accessibility_date_picker_year;
            R1.string.accessibility_datetime_picker_date = R.string.accessibility_datetime_picker_date;
            R1.string.accessibility_datetime_picker_time = R.string.accessibility_datetime_picker_time;
            R1.string.actionbar_share = R.string.actionbar_share;
            R1.string.actionbar_web_search = R.string.actionbar_web_search;
            R1.string.date_picker_dialog_clear = R.string.date_picker_dialog_clear;
            R1.string.date_picker_dialog_set = R.string.date_picker_dialog_set;
            R1.string.date_picker_dialog_title = R.string.date_picker_dialog_title;
            R1.string.date_picker_dialog_other_button_label = R.string.date_picker_dialog_other_button_label;
            R1.string.date_time_picker_dialog_title = R.string.date_time_picker_dialog_title;
            R1.string.media_player_error_button = R.string.media_player_error_button;
            R1.string.media_player_error_text_invalid_progressive_playback = R.string.media_player_error_text_invalid_progressive_playback;
            R1.string.media_player_error_text_unknown = R.string.media_player_error_text_unknown;
            R1.string.media_player_error_title = R.string.media_player_error_title;
            R1.string.media_player_loading_video = R.string.media_player_loading_video;
            R1.string.month_picker_dialog_title = R.string.month_picker_dialog_title;
            R1.string.profiler_error_toast = R.string.profiler_error_toast;
            R1.string.profiler_no_storage_toast = R.string.profiler_no_storage_toast;
            R1.string.profiler_started_toast = R.string.profiler_started_toast;
            R1.string.profiler_stopped_toast = R.string.profiler_stopped_toast;
            R1.string.time_picker_dialog_am = R.string.time_picker_dialog_am;
            R1.string.time_picker_dialog_hour_minute_separator = R.string.time_picker_dialog_hour_minute_separator;
            R1.string.time_picker_dialog_minute_second_separator = R.string.time_picker_dialog_minute_second_separator;
            R1.string.time_picker_dialog_second_subsecond_separator = R.string.time_picker_dialog_second_subsecond_separator;
            R1.string.time_picker_dialog_pm = R.string.time_picker_dialog_pm;
            R1.string.time_picker_dialog_title = R.string.time_picker_dialog_title;
            R1.string.week_picker_dialog_title = R.string.week_picker_dialog_title;
            R1.style.SelectPopupDialog = R.style.SelectPopupDialog;

            org.chromium.ui.R1.string.copy_to_clipboard_failure_message = R.string.copy_to_clipboard_failure_message;
            org.chromium.ui.R1.string.low_memory_error = R.string.low_memory_error;
            org.chromium.ui.R1.string.opening_file_error = R.string.opening_file_error;
            org.chromium.ui.R1.string.color_picker_button_more = R.string.color_picker_button_more;
            org.chromium.ui.R1.string.color_picker_hue = R.string.color_picker_hue;
            org.chromium.ui.R1.string.color_picker_saturation = R.string.color_picker_saturation;
            org.chromium.ui.R1.string.color_picker_value = R.string.color_picker_value;
            org.chromium.ui.R1.string.color_picker_button_set = R.string.color_picker_button_set;
            org.chromium.ui.R1.string.color_picker_button_cancel = R.string.color_picker_button_cancel;
            org.chromium.ui.R1.string.color_picker_dialog_title = R.string.color_picker_dialog_title;
            org.chromium.ui.R1.string.color_picker_button_red = R.string.color_picker_button_red;
            org.chromium.ui.R1.string.color_picker_button_cyan = R.string.color_picker_button_cyan;
            org.chromium.ui.R1.string.color_picker_button_blue = R.string.color_picker_button_blue;
            org.chromium.ui.R1.string.color_picker_button_green = R.string.color_picker_button_green;
            org.chromium.ui.R1.string.color_picker_button_magenta = R.string.color_picker_button_magenta;
            org.chromium.ui.R1.string.color_picker_button_yellow = R.string.color_picker_button_yellow;
            org.chromium.ui.R1.string.color_picker_button_black = R.string.color_picker_button_black;
            org.chromium.ui.R1.string.color_picker_button_white = R.string.color_picker_button_white;
            org.chromium.ui.R1.id.autofill_label = R.id.autofill_label;
            org.chromium.ui.R1.id.autofill_popup_window = R.id.autofill_popup_window;
            org.chromium.ui.R1.id.autofill_sublabel = R.id.autofill_sublabel;
            org.chromium.ui.R1.id.selected_color_view = R.id.selected_color_view;
            org.chromium.ui.R1.id.title = R.id.title;
            org.chromium.ui.R1.id.more_colors_button = R.id.more_colors_button;
            org.chromium.ui.R1.id.color_picker_advanced = R.id.color_picker_advanced;
            org.chromium.ui.R1.id.color_picker_simple = R.id.color_picker_simple;
            org.chromium.ui.R1.id.color_button_swatch = R.id.color_button_swatch;
            org.chromium.ui.R1.id.more_colors_button_border = R.id.more_colors_button_border;
            org.chromium.ui.R1.id.gradient= R.id.gradient;
            org.chromium.ui.R1.id.text = R.id.text;
            org.chromium.ui.R1.id.seek_bar = R.id.seek_bar;
            org.chromium.ui.R1.layout.autofill_text = R.layout.autofill_text;
            org.chromium.ui.R1.layout.color_picker_dialog_title = R.layout.color_picker_dialog_title;
            org.chromium.ui.R1.layout.color_picker_dialog_content = R.layout.color_picker_dialog_content;
            org.chromium.ui.R1.layout.color_picker_advanced_component = R.layout.color_picker_advanced_component;
            org.chromium.ui.R1.drawable.color_button_background = R.drawable.color_button_background;
            org.chromium.ui.R1.drawable.color_picker_advanced_select_handle = R.drawable.color_picker_advanced_select_handle;
            org.chromium.ui.R1.style.AutofillPopupWindow = R.style.AutofillPopupWindow;
            org.chromium.ui.R1.color.autofill_dark_divider_color = R.color.autofill_dark_divider_color;
            org.chromium.ui.R1.color.autofill_divider_color = R.color.autofill_divider_color;
            org.chromium.ui.R1.color.color_picker_border_color = R.color.color_picker_border_color;
            org.chromium.ui.R1.dimen.autofill_text_height = R.dimen.autofill_text_height;
            org.chromium.ui.R1.dimen.autofill_text_divider_height = R.dimen.autofill_text_divider_height;
            org.chromium.ui.R1.dimen.color_button_height = R.dimen.color_button_height;
        }
    }

    // Cache the factory both for efficiency, and ensure any one process gets all webviews from the
    // same provider.
    private static WebViewFactoryProvider sProviderInstance;
    private static final Object sProviderLock = new Object();

    public static WebViewFactoryProvider getProvider() {
        synchronized (sProviderLock) {
            // For now the main purpose of this function (and the factory abstraction) is to keep
            // us honest and minimize usage of WebView internals when binding the proxy.
            if (sProviderInstance != null) return sProviderInstance;

            Class<WebViewFactoryProvider> providerClass;
            try {
                providerClass = getFactoryClass();
            } catch (ClassNotFoundException e) {
                Log.e(LOGTAG, "error loading provider", e);
                throw new AndroidRuntimeException(e);
            }

            // This implicitly loads Preloader even if it wasn't preloaded at boot.
            if (Preloader.sPreloadedProvider != null &&
                Preloader.sPreloadedProvider.getClass() == providerClass) {
                sProviderInstance = Preloader.sPreloadedProvider;
                if (DEBUG) Log.v(LOGTAG, "Using preloaded provider: " + sProviderInstance);
                return sProviderInstance;
            }

            // The preloaded provider isn't the one we wanted; construct our own.
            StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskReads();
            try {
                sProviderInstance = providerClass.newInstance();
                if (DEBUG) Log.v(LOGTAG, "Loaded provider: " + sProviderInstance);
                return sProviderInstance;
            } catch (Exception e) {
                Log.e(LOGTAG, "error instantiating provider", e);
                throw new AndroidRuntimeException(e);
            } finally {
                StrictMode.setThreadPolicy(oldPolicy);
            }
        }
    }

    private static Class<WebViewFactoryProvider> getFactoryClass() throws ClassNotFoundException {
        return (Class<WebViewFactoryProvider>) Class.forName(CHROMIUM_WEBVIEW_FACTORY);
    }
}
