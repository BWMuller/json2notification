/*
 * Copyright (C) 2015 8tory, Inc
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

package com.bluelinelabs.logansquare.typeconverters;

import android.content.Intent;

import com.bluelinelabs.logansquare.models.SimpleIntent;
import com.bluelinelabs.logansquare.models.SimpleIntent$$JsonObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * <pre>
 * {
 *     "getActivity": true,
 *     "intent": {
 *         "action": "android.intent.action.VIEW",
 *         "uri": "https://play.google.com/store/apps/details?id=com.story8.android.gallery"
 *     }
 * }
 * </pre>
 */
public class IntentConverter implements TypeConverter<Intent> {
    @Override
    public Intent parse(JsonParser jsonParser) throws IOException {
        SimpleIntent simpleIntent = SimpleIntent$$JsonObjectMapper._parse(jsonParser);
        Intent intent = null;

        android.util.Log.d("json2notification", "uri:" + simpleIntent.uri);
        try {
            intent = Intent.parseUri(simpleIntent.uri, 0);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

        return intent;
    }

    @Override
    public void serialize(Intent intent, String fieldName, boolean writeFieldNameForObject,
            JsonGenerator jsonGenerator) throws IOException {
        android.util.Log.d("json2notification", "IntentConverter:serialize");
        if (intent == null) {
            android.util.Log.d("json2notification", "intent: null");
            return;
        }
        SimpleIntent simpleIntent = new SimpleIntent();
        simpleIntent.uri = intent.toUri(0);

        if (writeFieldNameForObject) jsonGenerator.writeFieldName(fieldName);
        SimpleIntent$$JsonObjectMapper._serialize((SimpleIntent) simpleIntent, jsonGenerator, true);
    }
}

