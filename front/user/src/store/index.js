import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        member: null,
        isLoggedIn: false,
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
