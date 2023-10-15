// rfce
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import RegisterApi from '../../services/RegisterApi';
import { withTranslation } from 'react-i18next';


// FUNCTION
function RegisterList({ t, i18n, props }) {

  // REDIRECT
  let navigate = useNavigate();

  // STATE
  const [registerApiListData, setRegisterApiListData] = useState([]); //unutma diziyi yaz

  // EFFECT
  useEffect(() => {
    RegisterApi.registerApiList()
      .then(
        (response) => {
           console.log(response);
           console.log(response.data);
           console.log(response.status);
           console.log(response.headers);
           if(response.status===200){
            setRegisterApiListData(response.data)
           }
           }
        )
      .catch((err) => { 
        console.log(err); });
  },[]) //end useEffect

  // FUNCTION

  const listManipulationAfter=()=>{
    RegisterApi.registerApiList()
    .then(
      (response) => {
         console.log(response);
         console.log(response.data);
         console.log(response.status);
         console.log(response.headers);
         if(response.status===200){
          setRegisterApiListData(response.data)
         }
         }
      )
    .catch((err) => { 
      console.log(err); });
  }

    // speed Data
    const speedData= async ()=>{
      const userData=prompt("Kaç tane veri eklemek istiyor sunuz ?")
      let response= await RegisterApi.registerApiSpeedData(userData);
      if(response.status===200){
        console.log("Speed Data");
        listManipulationAfter();
        navigate('/register/list')
         //window.location="/register/list"
      }
    }
  
  // DELETE ALL
  const deleteAll=()=>{

    if(window.confirm("Bütün verileri silmek istiyor musunuz ?")){
      RegisterApi.registerApiDeleteAll()
      .then((response)=>{
        if(response.status===200){
          listManipulationAfter();
          //navigate('/register/list')
          window.location="/register/list"
        }
      })
      .catch((err)=>{console.log(err);})
    }else{
      alert("Silinmedi")
    }
  }

  return (
    <React.Fragment>
      <br /><br /><br /><br />
      <h1>{t('register_list')}</h1>
      <Link className='btn btn-primary me-2' to="/register/create">{t('create')}</Link>
      <Link className='btn btn-secondary me-2' onClick={speedData}>{t('create_all')}</Link>
      <Link className='btn btn-danger' onClick={deleteAll}>{t('delete_all')}</Link>
      <table className='table table-hover table-striped table-responsive'>
        <thead>
          <tr>
            <th>{t('id')}</th>
            <th>{t('user_nickname')}</th>
            <th>{t('user_name')}</th>
            <th>{t('user_surname')}</th>
            <th>{t('user_email')}</th>
            <th>{t('user_password')}</th>
            <th>{t('user_is_passive')}</th>
            <th>{t('system_date')}</th>
           
            <th>{t('update')}</th>
            <th>{t('show')}</th>
            <th>{t('delete')}</th>
          </tr>
        </thead>
        <tbody>
          {
            registerApiListData.map((data)=>
            <tr key={data.id}>
            <td>{data.id}</td>
            <td>{data.registerNickName}</td>
            <td>{data.registerName}</td>
            <td>{data.registerSurname}</td>
            <td>{data.registerEmail}</td>
            <td>{data.registerPassword}</td>
            <td>{data.registerIsPassive}</td>
            <td>{data.systemDate}</td>
            <td><i className="fa-solid fa-pen-nib text-primary"></i></td>
            <td><i className="fa-solid fa-eye text-secondary"></i></td>
            <td><i className="fa-solid fa-trash text-danger"></i></td>
          </tr> 
            )
          }
        </tbody>
      </table>

      <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </React.Fragment>
  )
}

// i18n
export default withTranslation()(RegisterList) 