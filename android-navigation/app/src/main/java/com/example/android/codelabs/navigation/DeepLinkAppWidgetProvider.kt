/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.example.android.codelabs.navigation

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder

/**
 * App Widget that deep links you to the [DeepLinkFragment].
 */
class DeepLinkAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val remoteViews = RemoteViews(
            context.packageName,
            R.layout.deep_link_appwidget
        )

        val args = Bundle()
        args.putString("myarg", "From Widget")
        // TODO STEP 10 - DeepLinkBuilder를 사용해서 PendingIntent 구성하기
        // 기본적으로 NavDeepLingBuilder는 launcher activity를 실행시킨다.
        // 실행하고자 하는 activity의 context를 넘기거나, setComponentName 메서드에 activity 이름을 넘기면 오버라이딩 가능

        // deep link를 실행할 때 backstack은 넘기는 navigation graph에 의해 결정된다.
        // 만약 지정한 activity가 부모 activity를 가진다면 부모 activity들 또한 backstack에 포함된다.
        // app:startDestination을 통해 지정된 destination들을 사용해 backstack이 생성된다.
        // 복잡한 경우 nested navigation graph를 포함할 수 있는데,
        // 각 nested navigation graph에 설정된 app:startDestination 속성이 backstack을 결정한다.

        val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.mobile_navigation)
                .setDestination(R.id.deeplink_dest)
                .setArguments(args)
                .createPendingIntent()

        remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent)
        // TODO END STEP 10
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }
}
