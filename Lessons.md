# Lessons or points of note.

- No matching tests found in any candidate test task.
  Requested tests:
  Test pattern BaseApplicationTests in task :core:testing:test.
  This error is encountered when you use a kotlin directory and trying to run tests in Java directory.
- Run a postgres container using this command:
```bash
docker run -d --name vibestream_db -e POSTGRESS_USER=root -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=vibestream --mount=source=vibestream_pgdata,destination=/var/lib/postgresql/data -p 5432:5432
```