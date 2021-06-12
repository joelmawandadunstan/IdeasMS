import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
Vue.use(Vuex)

const state = {
  sidebarShow: 'responsive',
  sidebarMinimize: false,
  loginState: !!localStorage.getItem("accessToken")
}

const getters = {
  getLogginStatus: state => state.loginState
}

const actions = {
  login({ commit } , data){
      axios.post("api/v1/auth/signin", data)
          .then(response => {
              localStorage.setItem("accessToken", response.data.accessToken);
              commit('setLoginState', true);
          }).catch(err => {
              commit("setLoginState", false);
          })
  },
  logout({ commit }){
    localStorage.removeItem("accessToken");
    commit("setLoginState", false);
  }
}

const mutations = {
  toggleSidebarDesktop (state) {
    const sidebarOpened = [true, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarOpened ? false : 'responsive'
    
  },

  setLoginState : (state, changeState) => state.loginState = changeState,

  toggleSidebarMobile (state) {
    const sidebarClosed = [false, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarClosed ? true : 'responsive'
  },
  set (state, [variable, value]) {
    state[variable] = value
  }
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
})