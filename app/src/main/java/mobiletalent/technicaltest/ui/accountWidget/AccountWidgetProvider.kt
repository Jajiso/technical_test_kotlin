package mobiletalent.technicaltest.ui.accountWidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import mobiletalent.technicaltest.MainActivity
import mobiletalent.technicaltest.R

const val TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION"
const val EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM"

class AccountWidgetProvider: AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle
    ) {
        val views = RemoteViews(context.packageName, R.layout.account_widget)

        val minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)

        Toast.makeText(context, ""+minWidth, Toast.LENGTH_SHORT).show()
    }

    override fun onReceive(context: Context, intent: Intent) {
        val mgr: AppWidgetManager = AppWidgetManager.getInstance(context)
        if (intent.action == TOAST_ACTION) {
            val appWidgetId: Int = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            val viewIndex: Int = intent.getIntExtra(EXTRA_ITEM, 0)
            Toast.makeText(context, "Touched view $viewIndex", Toast.LENGTH_SHORT).show()
        }
        super.onReceive(context, intent)
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    //val widgetText = context.getString(R.string.appwidget_text)
    //views.setTextViewText(R.id.appwidget_text, widgetText)

    val clickIntent = Intent(context, MainActivity::class.java)
    val clickPendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0)

    val serviceIntent = Intent(context, AccountWidgetService::class.java).apply {
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
    }

    val rv = RemoteViews(context.packageName, R.layout.account_widget).apply {

        setOnClickPendingIntent(R.id.widget_button_visible, clickPendingIntent)
        setRemoteAdapter(R.id.widget_stackView, serviceIntent)
        setEmptyView(R.id.widget_stackView, R.id.widget_empty_textView)
    }

    appWidgetManager.updateAppWidget(appWidgetId, rv)
}