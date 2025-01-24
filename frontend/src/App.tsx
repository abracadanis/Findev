import './App.scss';

import Navbar from './components/Navbar';
import React from 'react';
import {Route, BrowserRouter, Routes} from "react-router-dom";
import MainPage from "./components/MainPage";
import UserList from "./components/UserList/UserList";
import ProjectInfo from "./components/ProjectInfo";
import ProjectsPage from "./components/ProjectsPage";
import UserInfo from "./components/UserInfo";

const App = () => {

    return (
        <div>
            <BrowserRouter>
                <Routes>

                    <Route
                        path = "/"
                        element={<MainPage/>}>
                    </Route>

                    <Route
                        path = "/projects"
                        element={<ProjectsPage/>}>
                    </Route>

                    <Route
                        path = "/users"
                        element={<UserList/>}>
                    </Route>

                    <Route
                        path = "/projects/:id"
                        element={<ProjectInfo/>}>
                    </Route>

                    <Route
                        path = "/users/:id"
                        element={<UserInfo/>}>
                    </Route>

                </Routes>

            </BrowserRouter>

        </div>
    );
};

export default App;
