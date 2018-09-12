package dev.grack.matchschedulefootbal.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.activity.DetailActivity
import dev.grack.matchschedulefootbal.adapter.NextMatchAdapter
import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.EventsResponse
import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import es.dmoral.toasty.Toasty
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchFragment : Fragment(), AnkoLogger {
    private lateinit var madapter: NextMatchAdapter
    private val events: MutableList<Event> = mutableListOf()

    @SuppressLint("PrivateResource")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(container) {

        madapter = NextMatchAdapter(events) {
            startActivity<DetailActivity>(DetailActivity.POSITIONEXTRA to it)
            longToast(it.strEvent.toString())
        }

        loadData()

        return UI {
            linearLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL

                swipeRefreshLayout {
                    onRefresh {
                        Handler().postDelayed({
                            setColorSchemeColors(
                                    resources.getColor(R.color.colorTab),
                                    resources.getColor(R.color.colorPrimaryDark),
                                    resources.getColor(R.color.colorAccent)
                            )
                            loadData()
                            isRefreshing = false
                        }, 3000)
                    }

                    recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = madapter
                    }
                }
            }
        }.view
    }

    private fun loadData() {
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getNextMatch()
        call.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                if (response.isSuccessful()) {
                    val event: List<Event> = response.body()?.events!!
                    var msg = ""
                    for (item: Event? in event.iterator()) {
                        msg = msg + item?.strEvent + "\n"
                        events.clear()
                        events.addAll(event)
                        madapter.notifyDataSetChanged()
                    }
                    Log.e("API: ", event.toString() + "\n")
                    Log.e("COUNT: ", msg)
                }
            }

            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                Toasty.error(ctx, "Failed to load match!").show()
            }
        })
    }
}