package com.fullstack405.bitcfinalprojectkotlin.fragments.attend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullstack405.bitcfinalprojectkotlin.adapter.AttendAllAdapter
import com.fullstack405.bitcfinalprojectkotlin.client.Client
import com.fullstack405.bitcfinalprojectkotlin.data.EventAppData
import com.fullstack405.bitcfinalprojectkotlin.data.UserAttendData
import com.fullstack405.bitcfinalprojectkotlin.databinding.FragmentAttendAllBinding
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AttendAllFragment : Fragment() {


    private lateinit var binding: FragmentAttendAllBinding
    private lateinit var attendAllAdapter: AttendAllAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAttendAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // activity에서 userId 추출
        val userId = activity?.intent!!.getLongExtra("userId",0)

        // 신청내역 리스트에 보이는 데이터
        lateinit var allList:MutableList<EventAppData>

        // db 연결버전
        Client.retrofit.findAttendList(userId).enqueue(object:retrofit2.Callback<List<EventAppData>>{
            override fun onResponse(call: Call<List<EventAppData>>, response: Response<List<EventAppData>>) {
                allList = response.body() as MutableList<EventAppData>

                attendAllAdapter = AttendAllAdapter(allList,userId)
                binding.recyclerViewAll.adapter = attendAllAdapter
                binding.recyclerViewAll.layoutManager = LinearLayoutManager(requireContext())

                attendAllAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<EventAppData>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })



    }

}