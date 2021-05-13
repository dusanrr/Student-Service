import { createStore } from 'vuex'

export default createStore({
  state: {
    studentList: null,
    professorList: null,
    subjectList: null,
    examList: null,
    examPeriodList: null
  },
  getters:{
    getStudentListState: (state) => {
      return state.studentList;
    },
    getProfessorListState: (state) => {
      return state.professorList;
    },
    getSubjectListState: (state) => {
      return state.subjectList;
    },
    getExamListState: (state) => {
      return state.examList;
    },
    getExamPeriodListState: (state) => {
      return state.examPeriodList;
    },
  },
  mutations: {
    storePageInfo(state, pageState) {
      state.studentList = {...pageState}, 
      state.professorList = {...pageState},
      state.subjectList = {...pageState},
      state.examList = {...pageState},
      state.examPeriodList = {...pageState};
    }
  },
  actions: {
    saveStudentListPageState(context, pageInfo) {
      context.commit('storePageInfo', pageInfo)
    },
    saveProfessorListPageState(context, pageInfo) {
      context.commit('storePageInfo', pageInfo)
    },
    saveSubjectListPageState(context, pageInfo) {
      context.commit('storePageInfo', pageInfo)
    },
    saveExamListPageState(context, pageInfo) {
      context.commit('storePageInfo', pageInfo)
    },
    saveExamPeriodListPageState(context, pageInfo) {
      context.commit('storePageInfo', pageInfo)
    }
  },
  modules: {
  }
})
