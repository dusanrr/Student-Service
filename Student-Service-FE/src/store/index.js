import { createStore } from 'vuex';
import { auth } from "./AuthModule";

export default createStore({
  modules: {
    auth,
  },
  state: {
    studentList: null,
    professorList: null,
    subjectList: null,
    examList: null,
    examPeriodList: null,
    examRegistrationList: null
  },
  getters:{
    getListStateByType: (state) => (type) => {
      switch (type) {
        case "student":
          return state.studentList;
        case "professor":
          return state.professorList;
        case "subject":
          return state.subjectList;
        case "exam":
          return state.examList;
        case "examPeriod":
          return state.examPeriodList;
        case "examRegistration":
          return state.examRegistrationList;
      }
    },
  },
  mutations: {
    storePageInfo(state, pageState) {
      state.studentList = {...pageState}, 
      state.professorList = {...pageState},
      state.subjectList = {...pageState},
      state.examList = {...pageState},
      state.examPeriodList = {...pageState},
      state.examRegistrationList = {...pageState};
    }
  },
  actions: {
    saveListState: (context, pageInfo) => {
      switch (pageInfo.type) {
        case "student":
          context.commit('storePageInfo', pageInfo);
          break;
        case "professor":
          context.commit('storePageInfo', pageInfo);
          break;
        case "subject":
          context.commit('storePageInfo', pageInfo);
          break;
        case "exam":
          context.commit('storePageInfo', pageInfo);
          break;
        case "examPeriod":
          context.commit('storePageInfo', pageInfo);
          break;
        case "examRegistration":
          context.commit('storePageInfo', pageInfo);
          break;
      }
    },
  },
})
