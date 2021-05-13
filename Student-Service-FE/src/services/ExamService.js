
  import { apiClient }  from '../main.js'
  
  export default {
    getAllExams: () => {
      return apiClient.get('/api/exams')
    },
    getAllProfessors: () => {
      return apiClient.get('/api/professors')
    },
    getAllSubjects: () => {
      return apiClient.get('/api/subjects')
    },
    getAllExamPeriods: () => {
      return apiClient.get('/api/examperiods')
    },
    getExamsByPage: ({ page, size}) => {
        return apiClient.get(`/api/exams/page?page=${page}&size=${size}`)
    },
    getExam: (examId) => {
        return apiClient.get('/api/exams/'+ examId)
    },
    insertExam: (exam) => {
      return apiClient.post('/api/exams', exam)
    },
    editExam: (exam) => {
        return apiClient.put('/api/exams/' + exam.id, exam)
    },
    delete(examId) {
        return apiClient.delete(`api/exams/${examId}`)
    }
  }