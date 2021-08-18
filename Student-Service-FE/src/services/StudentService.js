import { apiClient }  from '../main.js'
  
export default {
  getAllStudents: () => {
    return apiClient.get('/api/students')
  },
  getAllCities: () => {
    return apiClient.get('/api/cities')
  },
  getAllByPage: ({ page, size, search }) => {
      return apiClient.get(`/api/students/page?page=${page}&size=${size}&search=${search}`)
  },
  getStudent: (studentId) => {
      return apiClient.get('/api/students/'+ studentId)
  },
  insertStudent: (student) => {
    return apiClient.post('/api/students', student)
  },
  editStudent: (student) => {
      return apiClient.put('/api/students/' + student.id, student)
  },
  delete(studentId) {
      return apiClient.delete(`api/students/${studentId}`)
  }
}