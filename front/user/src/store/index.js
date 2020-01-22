import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        member: null,
        isLoggedIn: false,
        // member: {
        //     "createTime": "2020-01-22T16:34:35",
        //     "modifiedTime": "2020-01-22T16:34:35",
        //     "deleteTime": null,
        //     "memberNo": 1,
        //     "memberName": {"memberName": "민철"},
        //     "name": "민철"
        // },
        // isLoggedIn: true,
    },

    mutations: {
        setMember(state, member) {
            state.member = member
            state.isLoggedIn = !!member
        },

        removeMember(state) {
            state.member = null
            state.isLoggedIn = false
        }
    },

    actions: {
        setMember({commit}, member) {
            commit('setMember', member)
        },

        removeMember({commit}) {
            commit('removeMember')
        }
    }
})
