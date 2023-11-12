import axios from "axios";

// Sabit URL
const ROLE_URL = "/role/api/v1.0.0";
// hamitmizrak1@gmail.com Hm4444@.
// CLASS
class RegisterRolesApi {

    // CREATE
    // http://localhost:4444/role/api/v1/create
    registerRolesCreateApi(roleDto) {
        return axios.post(`${ROLE_URL}/create`, roleDto)
    }

    // LIST
    // http://localhost:4444/role/api/v1/list
    registerRolesListApi() {
        return axios.get(`${ROLE_URL}/list`);
    }

    // FIND
    // http://localhost:4444/role/api/v1/ind
    // http://localhost:4444/role/api/v1/find/0
    // http://localhost:4444/role/api/v1/find/-1
    // http://localhost:4444/role/api/v1/find/1
    registerRolesFindByIdApi(id) {
        return axios.get(`${ROLE_URL}/find/${id}`)
    }

    // DELETE
    // http://localhost:4444/role/api/v1/delete/1
    registerRolesDeleteApi(id) {
        return axios.delete(`${ROLE_URL}/delete/${id}`)
    }

    // UPDATE
    // http://localhost:4444/role/api/v1/update/1
    registerRolesUpdateApi(id, userDto) {
        return axios.put(`${ROLE_URL}/update/${id}`, userDto)
    }

       ////////////////////////////////////////////////////////////// 
    //Email üzerinden Role Bulmak
    registerRolesUserEmailFindRoles(emailAddress) {
        //return axios.get(`${ROLE_URL}/role/${emailAddress}`) //@RequestParam
        return axios.get(`${ROLE_URL}/role/${emailAddress}`) //@PathVariable
    }

} //end class userDto

// EXPORT
export default new RegisterRolesApi();