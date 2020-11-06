package mobiletalent.technicaltest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobiletalent.technicaltest.R
import mobiletalent.technicaltest.adapters.AccountAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        configView(root)
        return root
    }

    private fun configView(root: View) {

        adapter = AccountAdapter(root.context)

        var recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = adapter

        viewModel.getAccountList(root.context).observe(viewLifecycleOwner, Observer {
            adapter.setAccountList(it)
            adapter.notifyDataSetChanged()
        })

        var allAccountsBtn = root.findViewById<Button>(R.id.button_all_accounts)
        var onlyVisibleAccountsBtn = root.findViewById<Button>(R.id.button_visible_accounts)



        allAccountsBtn.setOnClickListener {
            adapter.filter(false)

        }
        onlyVisibleAccountsBtn.setOnClickListener {
            adapter.filter(true)

        }
    }

}