## CSE360 Group Project

### [Project Board](https://github.com/zakattack9/cse360-final-project/projects/1)

### Allowed Packages
- Java AWT
- Java Swing
- JFreeChart

### Desired Workflow
1. Go to the projects board and pick a task from the "To Do" column
2. Assign yourself to the task by clicking on the task and adding yourself under "Assignees"
3. Branch off of `master` branch or another related branch
4. Develop code as defined in the task description
5. After testing that the code works, push up code and ready the branch for merge
6. Create a pull request (PR) for your branch into another branch
7. Write a short description of what code changes the PR contains
8. Ensure that your merge branch is up to date with its base branch to prevent merge conflicts
9. Open the PR and ping the Slack to channel to let others know to review/approve the PR
10. Once required approvals have been granted, merge code into base branch
11. Close the issue/task in the project board (it will automatically move to the closed column)
12. Repeat from step 1

### Things to Note
- All code should be branched off of `master` or a subbranch and later merged back into `master` through a PR
- Read through the description of the task carefully to ensure that all things being requested are implemented; ping Slack channel for questions
- Step 8 of the "Desired Workflow" is automatically enforced by the repository and will prevent any direct pushes to `master` without a PR; if you are unsure of what step 8 is asking, read [here](https://github.com/zakattack9/git-branching-merging)
- When approving a PR, actually read through the changes that were made and add comments if necessary to start discussions on possible code errors and implementation problems
- Only source code should be pushed to the repo, extra files should be added to the `.gitignore` or not staged for commit

### Need to Submit
- Java source code
- screenshots of running project
- PERT or Burndown chart
- project report
  - process model used
  - how responsibilities were split
  - how project was monitored
- team evaluation

### Implementation Diagram
![diagram](./diagram.png?raw=true "Implementation Diagram")
As of now, we will not be creating the `Student` decorator classes
