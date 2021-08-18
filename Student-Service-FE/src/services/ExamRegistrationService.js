
  import { apiClient }  from '../main.js'
  
  export default {
    getAllExams: () => {
      return apiClient.get('/api/exams')
    },
    getStudentExam: (indexNumber) => {
      return apiClient.get(`/api/exams/indexNumber?indexNumber=${indexNumber}`)
    },
    getAllStudents: () => {
      return apiClient.get('/api/students')
    },
    getAllExamRegistrations: () => {
      return apiClient.get('/api/examregistrations')
    },
    getAllByPage: ({ page, size, search }) => {
        return apiClient.get(`/api/examregistrations/page?page=${page}&size=${size}&search=${search}`)
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