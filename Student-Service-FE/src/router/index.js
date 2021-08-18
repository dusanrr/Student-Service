import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import StudentList from '../views/student/StudentList.vue'
import StudentForm from '../views/student/StudentForm.vue'
import StudentDetails from '../views/student/StudentDetails.vue'
import ProfessorList from '../views/professor/ProfessorList.vue'
import ProfessorForm from '../views/professor/ProfessorForm.vue'
import ProfessorDetails from '../views/professor/ProfessorDetails.vue'
import SubjectList from '../views/subject/SubjectList.vue'
import SubjectForm from '../views/subject/SubjectForm.vue'
import SubjectDetails from '../views/subject/SubjectDetails.vue'
import ExamList from '../views/exam/ExamList.vue'
import ExamForm from '../views/exam/ExamForm.vue'
import ExamDetails from '../views/exam/ExamDetails.vue'
import ExamPeriodList from '../views/examperiod/ExamPeriodList.vue'
import ExamPeriodForm from '../views/examperiod/ExamPeriodForm.vue'
import ExamPeriodDetails from '../views/examperiod/ExamPeriodDetails.vue'
import ExamRegistrationList from '../views/examregistration/ExamRegistrationList.vue'
import ExamRegistrationForm from '../views/examregistration/ExamRegistrationForm.vue'
import ExamRegistrationDetails from '../views/examregistration/ExamRegistrationDetails.vue'

import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
// lazy-loaded
const Profile = () => import("../components/Profile.vue")

const routes = [
  {
    path: "/",
    component: Login,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
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
    path: '/student-details/:studentId',
    props:true,
    name: 'StudentDetails',
    component: StudentDetails
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
    path: '/professor-details/:professorId',
    props:true,
    name: 'ProfessorDetails',
    component: ProfessorDetails
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
    path: '/subject-details/:subjectId',
    props:true,
    name: 'SubjectDetails',
    component: SubjectDetails
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
    path: '/exam-details/:examId',
    props:true,
    name: 'ExamDetails',
    component: ExamDetails
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
    path: '/exam-period-details/:examPeriodId',
    props:true,
    name: 'ExamPeriodDetails',
    component: ExamPeriodDetails
  },
  {
    path: '/exam-period-form',    
    name: 'NewExamPeriod',
    component: ExamPeriodForm,
    props: { actionType: 'new'}
  },
  {
    path: '/exam-registration-list',
    name: 'ExamRegistrationList',
    component: ExamRegistrationList
  },
  {
    path: '/exam-registration-form/:examRegistrationId',
    props:true,
    name: 'EditExamRegistration',
    component: ExamRegistrationForm
  },
  {
    path: '/exam-registration-details/:examRegistrationId',
    props:true,
    name: 'ExamRegistrationDetails',
    component: ExamRegistrationDetails
  },
  {
    path: '/exam-registration-form',    
    name: 'NewExamRegistration',
    component: ExamRegistrationForm,
    props: { actionType: 'new'}
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
