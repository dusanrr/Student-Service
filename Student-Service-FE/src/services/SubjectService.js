import { apiClient }  from '../main.js'
  
export default {
  getAllSubjects: () => {
    return apiClient.get('/api/subjects')
  },
  getAllProfessors: () => {
    return apiClient.get('/api/professors')
  },
  getAllByPage: ({ page, size, search }) => {
      return apiClient.get(`/api/subjects/page?page=${page}&size=${size}&search=${search}`)
  },
  getSubject: (subjectId) => {
      return apiClient.get('/api/subjects/'+ subjectId)
  },
  insertSubject: (subject) => {
    return apiClient.post('/api/subjects', subject)
  },
  editSubject: (subject) => {
      return apiClient.put('/api/subjects/' + subject.id, subject)
  },
  delete(subjectId) {
      return apiClient.delete(`api/subjects/${subjectId}`)
  }
}