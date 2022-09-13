import ProjectItem from "./ProjectItem";

const ProjectList = (props) => {
    return <div className = "d-flex flex-row">
        {props.items.map(project => (
            <ProjectItem
            key = {project.id}
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