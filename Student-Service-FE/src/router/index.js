import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import StudentList from '../views/student/StudentList.vue'
import StudentForm from '../views/student/StudentForm.vue'
import ProfessorList from '../views/professor/ProfessorList.vue'
import ProfessorForm from '../views/professor/ProfessorForm.vue'
import SubjectList from '../views/subject/SubjectList.vue'
import SubjectForm from '../views/subject/SubjectForm.vue'
import ExamList from '../views/exam/ExamList.vue'
import ExamForm from '../views/exam/ExamForm.vue'
import ExamPeriodList from '../views/examperiod/ExamPeriodList.vue'
import ExamPeriodForm from '../views/examperiod/ExamPeriodForm.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/student-list',
    name: 'StudentList',
    component: StudentList
  },
  {
    path: '/student-form/:studentId',
    props:true,
    name: 'EditStudent',
    component: StudentForm
  },
  {
    path: '/student-form',    
    name: 'NewStudent',
    component: StudentForm,
    props: { actionType: 'new'}
  },
  {
    path: '/professor-list',
    name: 'ProfessorList',
    component: ProfessorList
  },
  {
    path: '/professor-form/:professorId',
    props:true,
    name: 'EditProfessor',
    component: ProfessorForm
  },
  {
    path: '/professor-form',    
    name: 'NewProfessor',
    component: ProfessorForm,
    props: { actionType: 'new'}
  },
  {
    path: '/subject-list',
    name: 'SubjectList',
    component: SubjectList
  },
  {
    path: '/subject-form/:subjectId',
    props:true,
    name: 'EditSubject',
    component: SubjectForm
  },
  {
    path: '/subject-form',    
    name: 'NewSubject',
    component: SubjectForm,
    props: { actionType: 'new'}
  },
  {
    path: '/exam-list',
    name: 'ExamList',
    component: ExamList
  },
  {
    path: '/exam-form/:examId',
    props:true,
    name: 'EditExam',
    component: ExamForm
  },
  {
    path: '/exam-form',    
    name: 'NewExam',
    component: ExamForm,
    props: { actionType: 'new'}
  },
  {
    path: '/exam-period-list',
    name: 'ExamPeriodList',
    component: ExamPeriodList
  },
  {
    path: '/exam-period-form/:examPeriodId',
    props:true,
    name: 'EditExamPeriod',
    component: ExamPeriodForm
  },
  {
    path: '/exam-period-form',    
    name: 'NewExamPeriod',
    component: ExamPeriodForm,
    props: { actionType: 'new'}
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
