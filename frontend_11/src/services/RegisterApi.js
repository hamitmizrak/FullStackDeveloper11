
// Axios
import axios from "axios";

// Persist Data Url
const REGISTER_URL="/register/api/v1.0.0";

class RegisterApi {

    // SPEED DATA
    // http://localhost:4444/register/api/v1.0.0/speed/data/10
    // @GetMapping("/speed/data/{id}")
   registerApiSpeedData(id) {
    return axios.get(`${REGISTER_URL}/speed/data/${id}`)
    }

    // DELETE ALL
    // http://localhost:4444/register/api/v1.0.0/delete/all
    // target@GetMapping("/delete/all")
     registerApiDeleteAll() {
        return axios.get(REGISTER_URL+"/delete/all")
    }
    ///////////////////////////////////////////////////////////////////////////

    // SEARCH
    // LOGIN
    // http://localhost:4444/register/api/v1.0.0/search?surname=mizrak
    //@GetMapping("/search")
    loginApiFindBySurname( surname) {
        return axios.get((`${REGISTER_URL}/search?${surname}`));
    }

    ///////////////////////////////////////////////////////////////////////////
    // CREATE
    // http://localhost:4444/register/api/v1.0.0/create/roleId
    // http://localhost:4444/register/api/v1.0.0/create/1
    // @PostMapping("/create")
    registerApiCreate(registerDto) {
        // 
        return axios.post(`${REGISTER_URL}/create/4`,  registerDto,{
            method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(registerDto),
        })
    }

    // CREATE ADMIN
    registerAdminApiCreate(role_id,registerDto) {
        // 
        return axios.post(`${REGISTER_URL}/create/${role_id}`,  registerDto,{
            method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(registerDto),
        })
    }


    // LIST
    // http://localhost:4444/register/api/v1.0.0/list
    // @GetMapping("/list")
     registerApiList() {
        return axios.get(`${REGISTER_URL}/list`)
    }

    // FIND BY ID
    // http://localhost:4444/register/api/v1.0.0/find/1
    // @GetMapping("/find/{id}")
    registerApiFindById(id) {
        return axios.get(`${REGISTER_URL}/find/${id}`)
    }

    // UPDATE
    // http://localhost:4444/register/api/v1.0.0/update/1
    // @PutMapping(value = "/update/{id}")
    registerApiUpdate(  id, registerDto) {
        return axios.put(`${REGISTER_URL}/update/${id}`,registerDto)
    }

    // DELETE BY ID
    // http://localhost:4444/register/api/v1.0.0/delete/1
    // @DeleteMapping("/delete/{id}")
     registerApiDeleteById(id) {
        return axios.delete(`${REGISTER_URL}/delete/${id}`)
    }

} //end class

// NEW
export default new  RegisterApi()
