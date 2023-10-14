// rcc
import React, { Component } from 'react';

class Footer extends Component {

    // Display Name
    static displayName = "FooterProject";

    // CONSTRUCTOR
    constructor(props) {
        super(props);

        // STATE
        this.state = {

        }

        // BIND
        this.newDate=this.newDate.bind(this);
    }

    // CDM

    //FUNCTION
        //FUNCTION
        newDate() {
            return new Date().getFullYear();
        }

    render() {
        return (
            <React.Fragment>
                <footer className='bg-dark text-center text-white fixed-bottom'>
                    <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
                        ©2021- {this.newDate()} - <a className='text-white' href=''>{this.props.copy}</a>
                    </div>
                </footer>
            </React.Fragment>
        );
    }
}

export default Footer;