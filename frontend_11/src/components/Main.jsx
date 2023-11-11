import React, { Component } from 'react';

// CSS
import './main.css'

// IMAGE
import sunRise from '../image/sunrise.jpg'
import { Link } from 'react-router-dom';

// Main Class
class Main extends Component {


    // Display Name
    static displayName = "Main_Project";

    // CONSTRUCTOR
    constructor(props) {
        super(props);

        // STATE
        this.state = {
          
        }
        // BIND
    }

    //FUNCTION
    render() {
        return (
            <React.Fragment >

            <div id="light">
                <img src={sunRise} alt="" width="100%" />

                <p>Lorem ipsatem libero, tempora adipisci! Nam explicabo excepturi voluptas at sapiente! Voluptate porro explicabo magnam odio cum expedita voluptatum impedit vel ab.
            </p>
            <br />
            <Link to="/register/list" className='btn btn-primary'>Register List</Link>
           <br /><br /><br /><br /><br />
           </div>
            </React.Fragment>
        ); // end return
    } //end render
} //end class

export default Main;