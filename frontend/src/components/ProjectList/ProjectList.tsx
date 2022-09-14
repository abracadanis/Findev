import ProjectItem from "./ProjectItem";
import {IProject, IProject1} from "../../models/ProjectModel";

interface ProjectProps{
    project: IProject1 []
}

const ProjectList = (props: ProjectProps) => {
    const projectItems = () => {

    }
    return <div className = "d-flex flex-row">
        {props.project.map(project => (
            <ProjectItem
            id = {project.id}
            title = {project.title}
            description = {project.description}>
                {project.title}
                {project.description}
            </ProjectItem>
        ))}

    </div>
}

export default ProjectList;