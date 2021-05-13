
  import { apiClient }  from '../main.js'
  
  export default {
    getAllExamPeriods: () => {
      return apiClient.get('/api/examperiods')
    },
    getExamPeriodsByPage: ({ page, size}) => {
        return apiClient.get(`/api/examperiods/page?page=${page}&size=${size}`)
    },
    getExamPeriod: (examPeriodId) => {
        return apiClient.get('/api/examperiods/'+ examPeriodId)
    },
    insertExamPeriod: (examPeriod) => {
      return apiClient.post('/api/examperiods', examPeriod)
    },
    editExamPeriod: (examPeriod) => {
        return apiClient.put('/api/examperiods/' + examPeriod.id, examPeriod)
    },
    delete(examPeriodId) {
        return apiClient.delete(`api/examperiods/${examPeriodId}`)
    }
  }