package mobiletalent.technicaltest.ui.accountWidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import mobiletalent.technicaltest.R
import mobiletalent.technicaltest.domain.AccountRepository

class AccountWidgetService: RemoteViewsService() {

    override fun onGetViewFactory(p0: Intent): RemoteViewsFactory {
        return AccountWidgetItemFactory(applicationContext, p0)
    }

    class AccountWidgetItemFactory(private val context: Context, val intent: Intent) : RemoteViewsFactory {
        val repo = AccountRepository()
        private lateinit var widgetItems: List<String>

        override fun onCreate() {
            widgetItems = List(repo.getAccountsFromAssets(context).size) {
                    index -> repo.getAccountsFromAssets(context)[index].toString()
            }

        }

        override fun getLoadingView(): RemoteViews {
            return RemoteViews(context.packageName, R.layout.account_widget_item).apply {
                setTextViewText(R.id.account_widget_item_text, "Loading...")
            }
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun onDataSetChanged() {
        }

        override fun hasStableIds(): Boolean {
            return true
        }

        override fun getViewAt(p0: Int): RemoteViews {
            return RemoteViews(context.packageName, R.layout.account_widget_item).apply {
                setTextViewText(R.id.account_widget_item_text, widgetItems[p0])
            }
        }

        override fun getCount(): Int {
            return widgetItems.size
        }

        override fun getViewTypeCount(): Int {
            return 1
        }

        override fun onDestroy() {
        }

    }
}