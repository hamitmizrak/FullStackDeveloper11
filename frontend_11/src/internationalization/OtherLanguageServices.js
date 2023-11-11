import axios from "axios";


//Language
class OtherLanguageServices {

    // language Flag button
    headerLanguageServices(language) {
        axios.defaults.headers['accept-language'] = language;
    } //end headerLanguageServices
}//end class

// EXPORT
export default new OtherLanguageServices()