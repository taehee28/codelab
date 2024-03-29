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

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.onNavDestinationSelected

/**
 * Fragment used to show how to navigate to another destination
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * destination(프래그먼트)을 지정해서 원하는 화면으로 navigate할 수 있지만,
        * destination을 연결한 action을 지정해서도 navigate할 수 있다.
        *
        * 각 프래그먼트의 action의 id는 중복할 수 있는데,
        * 같은 action id여도 navigate를 실행하는 프래그먼트에 따라서 동작이 바뀌게 된다.
        * -> context에 따른 동작
        * */

        //TODO STEP 5 - OnClickListener 설정하기. Navigation.createNavigateOnClickListener()을 사용할 수도 있다.
        /*val button = view.findViewById<Button>(R.id.navigate_destination_button)
        button?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null)
        }*/

        /*
        // setOnClickListener에 람다를 넘기지 않고
        // OnClickListener의 인스턴스를 만들어 넘길 수도 있다
        button?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.flow_step_one_dest, null)
        )
        */

        //TODO END STEP 5

        //TODO STEP 6 - NavOptions 설정하기
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        view.findViewById<Button>(R.id.navigate_destination_button)?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, options)
        }
        //TODO END STEP 6

        //TODO STEP 7.2 - action을 사용해서 navigate 하도록 업데이트
        view.findViewById<Button>(R.id.navigate_action_button)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        )
        //TODO END STEP 7.2

        // args를 안전하게 넘기는 방법: Directions 클래스 사용하기
        // action을 가지는 모든 destination에 대해 Directions 클래스가 생성되며,
        // 해당 destination이 가지는 action들에 대한 메서드가 포함되어 있다.
        /*
        view.findViewById<Button>(R.id.navigate_action_button)?.setOnClickListener {
            val flowStepNumberArgs = 1
            val action = HomeFragmentDirections.nextAction(flowStepNumberArgs)
            findNavController().navigate(action)
        }
        */
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
    }
}
