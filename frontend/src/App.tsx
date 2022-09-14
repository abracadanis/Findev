import './App.scss';

import Navbar from "./components/Navbar";
import ProjectList from "./components/ProjectList/ProjectList";
import React, {useState} from "react";
import projectItem from "./components/ProjectList/ProjectItem";

const App = (props: any) => {

    const projects = [
        {"id": 123, "title": "title", "description": "description"},
        {"id": 321, "title": "title11", "description": "description11"},
    ]

  return (
      <div>
        <Navbar/>
        <ProjectList project={projects}/>
      </div>
  );
}

export default App;
