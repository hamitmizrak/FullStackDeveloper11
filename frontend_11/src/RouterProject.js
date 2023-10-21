import React, { Component } from 'react';
import Header from './components/Header';
import Main from './components/Main';
import Footer from './components/Footer';
import { Navigate, Route, Routes } from 'react-router-dom';
import RegisterList from './components/register/RegisterList';
import RegisterCreate from './components/register/RegisterCreate';
import RegisterView from './components/register/RegisterView';
import RegisterUpdate from './components/register/RegisterUpdate';
import { withTranslation } from 'react-i18next';



class RouterProject extends Component {

    // Display Name
    static displayName = "RouterProject";

    // CONSTRUCTOR
    constructor(props) {
        super(props);

        // STATE
        this.state = {
        }

        // BIND
    }

    // CDM

    //FUNCTION
    newDate() {
        return new Date().toLocaleString();
    }

    render() {
        // object destructing
        const { t } = this.props;
        return (
            <React.Fragment>

                {/* HEADER */}
                <Header logo="fa-solid fa-cloud"></Header>
                    {/* ROUTING */}
                    
                    <div className='container'>
                    <Routes>
                        {/* ROOT */}
                        {/* <Route exact={true} path="/" /> */}
                        <Route path="/" element={<Main/>} />


                        {/* LOGIN */}
                        <Route path={"/login"} element={<loginPage />} />

                        {/* REGISTER */}
                        <Route path={"/register/list"} element={<RegisterList name="register List" />} />
                        <Route path={"/register/create"} element={<RegisterCreate name="register create" />} />
                        <Route path={"/register/view/:id"} element={<RegisterView name="register view" />} />
                        <Route path={"/register/update/:id"} element={<RegisterUpdate name="register update" />} />

                        <Route path={"*"} element={<Navigate to={"/"} />} />
                    </Routes>
                    </div>

                {/* FOOTER */}
                <Footer copy="&copy; Bütün haklar saklıdır"></Footer>

            </React.Fragment>
        ); //end return
    } //end render
} // end class 

// Export
// Wrapper Higher ORder Component (i18n)
export default withTranslation()(RouterProject);