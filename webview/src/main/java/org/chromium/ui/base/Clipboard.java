// Copyright 2013 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.ui.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.R1;

/**
 * Simple proxy that provides C++ code with an access pathway to the Android
 * clipboard.
 */
@JNINamespace("ui")
public class Clipboard {
    // Necessary for coercing clipboard contents to text if they require
    // access to network resources, etceteras (e.g., URI in clipboard)
    private final Context mContext;

    private final ClipboardManager mClipboardManager;

    /**
     * Use the factory constructor instead.
     *
     * @param context for accessing the clipboard
     */
    public Clipboard(final Context context) {
        mContext = context;
        mClipboardManager = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * Returns a new Clipboard object bound to the specified context.
     *
     * @param context for accessing the clipboard
     * @return the new object
     */
    @CalledByNative
    private static Clipboard create(final Context context) {
        return new Clipboard(context);
    }

    /**
     * Emulates the behavior of the now-deprecated
     * {@link android.text.ClipboardManager#getText()} by invoking
     * {@link android.content.ClipData.Item#coerceToText(Context)} on the first
     * item in the clipboard (if any) and returning the result as a string.
     * <p>
     * This is quite different than simply calling {@link Object#toString()} on
     * the clip; consumers of this API should familiarize themselves with the
     * process described in
     * {@link android.content.ClipData.Item#coerceToText(Context)} before using
     * this method.
     *
     * @return a string representation of the first item on the clipboard, if
     *         the clipboard currently has an item and coercion of the item into
     *         a string is possible; otherwise, <code>null</code>
     */
    @SuppressWarnings("javadoc")
    @CalledByNative
    private String getCoercedText() {
        final ClipData clip = mClipboardManager.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            final CharSequence sequence = clip.getItemAt(0).coerceToText(mContext);
            if (sequence != null) {
                return sequence.toString();
            }
        }
        return null;
    }

    /**
     * Gets the HTML text of top item on the primary clip on the Android clipboard.
     *
     * @return a Java string with the html text if any, or null if there is no html
     *         text or no entries on the primary clip.
     */
    @CalledByNative
    private String getHTMLText() {
        if (isHTMLClipboardSupported()) {
            final ClipData clip = mClipboardManager.getPrimaryClip();
            if (clip != null && clip.getItemCount() > 0) {
                return clip.getItemAt(0).getHtmlText();
            }
        }
        return null;
    }

    /**
     * Emulates the behavior of the now-deprecated
     * {@link android.text.ClipboardManager#setText(CharSequence)}, setting the
     * clipboard's current primary clip to a plain-text clip that consists of
     * the specified string.
     *
     * @param label will become the label of the clipboard's primary clip
     * @param text  will become the content of the clipboard's primary clip
     */
    public void setText(final String label, final String text) {
        setPrimaryClipNoException(ClipData.newPlainText(label, text));
    }

    /**
     * Emulates the behavior of the now-deprecated
     * {@link android.text.ClipboardManager#setText(CharSequence)}, setting the
     * clipboard's current primary clip to a plain-text clip that consists of
     * the specified string.
     *
     * @param text will become the content of the clipboard's primary clip
     */
    @CalledByNative
    public void setText(final String text) {
        setText(null, text);
    }

    /**
     * Writes HTML to the clipboard, together with a plain-text representation
     * of that very data. This API is only available in Android JellyBean+ and
     * will be a no-operation in older versions.
     *
     * @param html  The HTML content to be pasted to the clipboard.
     * @param label The Plain-text label for the HTML content.
     * @param text  Plain-text representation of the HTML content.
     */
    public void setHTMLText(final String html, final String label, final String text) {
        if (isHTMLClipboardSupported()) {
            setPrimaryClipNoException(ClipData.newHtmlText(label, text, html));
        }
    }

    /**
     * Writes HTML to the clipboard, together with a plain-text representation
     * of that very data. This API is only available in Android JellyBean+ and
     * will be a no-operation in older versions.
     *
     * @param html The HTML content to be pasted to the clipboard.
     * @param text Plain-text representation of the HTML content.
     */
    @CalledByNative
    public void setHTMLText(final String html, final String text) {
        setHTMLText(html, null, text);
    }

    @CalledByNative
    private static boolean isHTMLClipboardSupported() {
        return ApiCompatibilityUtils.isHTMLClipboardSupported();
    }

    private void setPrimaryClipNoException(ClipData clip) {
        try {
            mClipboardManager.setPrimaryClip(clip);
        } catch (Exception ex) {
            // Ignore any exceptions here as certain devices have bugs and will fail.
            String text = mContext.getString(R1.string.copy_to_clipboard_failure_message);
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        }
    }
}
