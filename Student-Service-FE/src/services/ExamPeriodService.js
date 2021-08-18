
  import { apiClient }  from '../main.js'
  
  export default {
    getAllExamPeriods: () => {
      return apiClient.get('/api/examperiods')
    },
    getAllByPage: ({ page, size, search }) => {
        return apiClient.get(`/api/examperiods/page?page=${page}&size=${size}&search=${search}`)
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