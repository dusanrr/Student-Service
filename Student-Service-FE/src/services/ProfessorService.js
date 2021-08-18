import { apiClient }  from '../main.js'
  
export default {
  getAllProfessors: () => {
    return apiClient.get('/api/professors')
  },
  getAllCities: () => {
    return apiClient.get('/api/cities')
  },
  getAllTitles: () => {
    return apiClient.get('/api/titles')
  },
  getAllByPage: ({ page, size, search }) => {
      return apiClient.get(`/api/professors/page?page=${page}&size=${size}&search=${search}`)
  },
  getProfessor: (professorId) => {
      return apiClient.get('/api/professors/'+ professorId)
  },
  insertProfessor: (professor) => {
    return apiClient.post('/api/professors', professor)
  },
  editProfessor: (professor) => {
      return apiClient.put('/api/professors/' + professor.id, professor)
  },
  delete(professorId) {
      return apiClient.delete(`api/professors/${professorId}`)
  }
}