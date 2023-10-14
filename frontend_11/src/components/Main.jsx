import React, { Component } from 'react';

// CSS
import './main.css'

// IMAGE
import sunRise from '../img/sunrise.jpg'

class Main extends Component {


    // Display Name
    static displayName = "MainProject";

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


    render() {
        return (
            <React.Fragment>


                <img src={sunRise} alt="" />

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur quo obcaecati commodi iusto quibusdam distinctio id voluptates cumque corrupti culpa eius repellendus nihil, reprehenderit dolores, iure odio ex facilis vel.
                    Iure quis harum molestias magni, exercitationem facilis deserunt praesentium error officia nemo consectetur corporis beatae dolore aliquam, ipsam cumque iste nam, quia dolores voluptatum sit. Voluptatum commodi praesentium harum quasi.
                    Nihil culpa quibusdam, accusamus illo soluta ipsam eius cupiditate, nulla quia saepe ratione amet fugiat necessitatibus ut voluptatibus labore et repellat quidem? Neque voluptatibus qui numquam voluptas nihil sint cumque.
                usamus dicta ab voluptatem libero, tempora adipisci! Nam explicabo excepturi voluptas at sapiente! Voluptate porro explicabo magnam odio cum expedita voluptatum impedit vel ab.
            </p>
            </React.Fragment>
        );
    }
}

export default Main;