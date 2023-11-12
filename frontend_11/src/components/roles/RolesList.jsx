// rfce
// REACT
import React, {useEffect, useState} from 'react'

// LINK
import {Link, useNavigate} from 'react-router-dom'

// REGISTER API
import RegisterRolesApi from '../../services/RegisterRolesApi';

// I18N
import {withTranslation} from 'react-i18next';
import axios, {AxiosError} from 'axios';
import RegisterApi from "../../services/RegisterApi";

// FUNCTION
function RolesList({t, i18n, props}) {

    // REDIRECT
    let navigate = useNavigate();

    // STATE
    const [registerRolesApiListData, setRegisterRolesApiListData] = useState([]); //unutma diziyi yaz

    // ERROR
    const [error, setError] = useState(undefined);

    // EFFECT
    useEffect(() => {
        //1 .YOL
        // RegisterRolesApi.registerRolesListApi()
        //   .then(
        //     (response) => {
        //       console.log(response);
        //       console.log(response.data);
        //       console.log(response.status);
        //       console.log(response.headers);
        //       if (response.status === 200) {
        //         setRegisterRolesApiListData(response.data)
        //       }else
        //       Promise.reject();
        //     }
        //   )
        //   .catch((err) => {
        //     console.log(err);
        //   });
        //2.YOL
        fetchRolesList();
    }, []) //end useEffect

    // Fetch users from API
    const fetchRolesList = async () => {
        // Hataları gösterme
        setError(null);
        try {
            const response = await RegisterRolesApi.registerRolesListApi()//fetch('https://api.example.com/users');
            setRegisterRolesApiListData(response.data)
           // console.error( AxiosError.name);
        } catch (err) {
            //console.error('Error fetching users:', err);
            //console.error(err.response.data.validationErrors);
            setError(err.response.data.validationErrors)
        }
    };

    // FUNCTION
    // LIST AFTER LOADING
    const listManipulationAfter = () => {
        RegisterRolesApi.registerRolesListApi()
            .then(
                (response) => {
                    // console.log(response);
                    // console.log(response.data);
                    // console.log(response.status);
                    // console.log(response.headers);
                    if (response.status === 200) {
                        setRegisterRolesApiListData(response.data)
                    }
                }
            )
            .catch((err) => {
                console.log(err);
            });
    }

    ////////////////////////////


    //ROLE DELETE
    const setDeleteRegister = (id) => {
        if (window.confirm(id + " silmek istiyor musunuz ?")) {
            // 1.YOL

            RegisterRolesApi.registerRolesDeleteApi(id)
                .then((response) => {
                    if (response.status === 200) {
                        listManipulationAfter();
                        navigate('/role/list')
                        //window.location = "/role/list"
                    }
                })
                .catch((err) => {
                    console.error(err);
                    navigate('/role/list')
                    //window.location = "/role/list"
                });
        } else {
            alert(id + " nolu data silinmedi !!!");
            //navigate('/role/list')
            window.location = "/role/list"
        }

        // 2.YOL (delete axios yazarak)
        // axios.delete(" http://localhost:4444/role/api/v1.0.0/delete/"+id).then().catch();
    }


    //Error
    const classNameData ={error}  ? "is-invalid form-control mb-3" : "form-control mb-3";

    // RETURN
    return (
        <React.Fragment>
            <br/><br/><br/><br/>
            <h1>{t('register_list')}</h1>
            <Link className='btn btn-primary me-2' to="/role/create">{t('create')}</Link>
            <table className='table table-striped table-responsive mb-5'>
                <thead>
                <tr>
                    <th>{t('id')}</th>
                    <th>{t('role_name')}</th>
                    <th>{t('system_date')}</th>
                    <th>{t('update')}</th>
                    <th>{t('delete')}</th>
                </tr>
                </thead>
                <tbody>
                {
                    registerRolesApiListData.map((data) =>
                        <tr key={data.id}>
                            <td>{data.id}</td>
                            <td>{data.roleName}</td>
                            <td>{data.systemDate}</td>
                            <td>
                                <Link to={`/role/update/${data.id}`}>
                                    <i className="fa-solid fa-pen-nib text-primary"></i>
                                </Link>
                            </td>

                            <td>
                                <Link>
                                    <i onClick={() => setDeleteRegister(data.id)}
                                       className="fa-solid fa-trash text-danger"></i>
                                </Link>
                            </td>
                        </tr>
                    )
                }
                </tbody>
            </table>

            {
                error ?
                    <div className="invalid-feedback44">data {error}</div>
                    : ""

            }

        </React.Fragment>
    ) //end return
} //end function

// i18n
export default withTranslation()(RolesList)