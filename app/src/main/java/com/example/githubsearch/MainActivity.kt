package com.example.githubsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.FragmentSearch.AdapterSearchRecyclerView
import com.example.githubsearch.Model.GitHubResponseUsers
import com.example.githubsearch.Model.User
import com.example.githubsearch.api.GitHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG_FROM_ACTIVITY = "ACTIVITY_MAIN"
const val TAG_FOR_SEARCH_USERS = "SEARCH_USERS"

class MainActivity: AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView

    private var adapterForRecyclerView = AdapterSearchRecyclerView()

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setEditText()
        setRecyclerView()
    }

    fun init(){
        searchEditText = findViewById(R.id.id_edittext_search)
        recyclerView = findViewById(R.id.id_recyclerview_search)

        searchViewModel.currentUsersListViewModel.observe(this, {
            //adapterForRecyclerView.setList(it)
            Log.d(TAG_FROM_ACTIVITY, "Активити. Значение currentListLiveData: $it")
            if (it.isNotEmpty()){
                adapterForRecyclerView.setList(it)
            } else{

            }
        })
    }

    fun setEditText(){
        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG_FOR_SEARCH_USERS, "В активити вызван onTextChanged для editText")

                if (s != null){
                    if (s.length > 3){
                        searchViewModel.search(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun setRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterForRecyclerView
    }

}