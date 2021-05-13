
  import { apiClient }  from '../main.js'
  
  export default {
    getAllExamRegistrations: () => {
      return apiClient.get('/api/examregistrations')
    },
    getExamRegistrationsByPage: ({ page, size}) => {
        return apiClient.get(`/api/examregistrations/page?page=${page}&size=${size}`)
    },
    getExamRegistration: (examRegistrationId) => {
        return apiClient.get('/api/examregistrations/'+ examRegistrationId)
    },
    insertExamRegistration: (examRegistration) => {
      return apiClient.post('/api/examregistrations', examRegistration)
    },
    editExamRegistration: (examRegistration) => {
        return apiClient.put('/api/examregistrations/' + examRegistration.id, examRegistration)
    },
    delete(examRegistrationId) {
        return apiClient.delete(`api/examregistrations/${examRegistrationId}`)
    }
  }