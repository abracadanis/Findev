import './App.scss';

import Navbar from "./components/Navbar";
import ProjectList from "./components/ProjectList/ProjectList";
import React, {useEffect, useState} from "react";
import projectItem from "./components/ProjectList/ProjectItem";
import {Api, ApplicationApi, Configuration} from "./openapi";

const configuration = new Configuration({
    basePath: "http://localhost:8180/api/findev"
})

const api = new ApplicationApi(configuration)

const App = (props: any) => {

    useEffect(() => {
        api.getUsers().then(data => {setUsers(data)})
    }, [])

    const [users, setUsers] = useState<Api.UserSo[]>([])

    const projects = [
        {"id": 123, "title": "title", "description": "description"},
        {"id": 321, "title": "title11", "description": "description11"},
    ]



  return (
      <div>
        <Navbar/>
        <ProjectList project={projects}/>
          {JSON.stringify(users)}
      </div>
  );
}

export default App;
