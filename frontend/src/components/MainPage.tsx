import Navbar from "./Navbar";
import React from "react";

const MainPage = () => {

    return (
        <div>
            <Navbar/>
            <div className="text-center align-items-center justify-content-center  d-block">
                <p className = "fs-1 m-4">! Welcome page !</p>
                <p className = "fs-5 fw-lighter"> ... some images ... </p>
                <p className= "fs-2"> ... some text about us ... </p>
                <p className = "fs-5 fw-lighter"> ... some images again ... </p>
            </div>
        </div>
    );
}

export default MainPage;