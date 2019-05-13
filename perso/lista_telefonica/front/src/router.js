import Vue from 'vue'
import Router from 'vue-router'
import Contacts from "./views/Contacts";
import ContactInfo from "./views/ContactInfo";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'contacts',
      component: Contacts
    },
    {
      path: '/contactInfo/:id',
      name: 'contactInfo/:id',
      component: ContactInfo
    }
  ]
})
