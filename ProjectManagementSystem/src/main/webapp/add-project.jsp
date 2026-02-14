<!DOCTYPE html>
<html>
<head>
    <title>Add Project</title>

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="container mt-5">

<h2>Add Project</h2>

<form action="project" method="post">

    <input type="hidden" name="action" value="add">

    <div class="mb-3">
        <label>Project Name</label>
        <input type="text" name="name" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Description</label>
        <input type="text" name="description" class="form-control" required>
    </div>

    <button type="submit" class="btn btn-primary">
        Save Project
    </button>

    <a href="project?action=list" class="btn btn-secondary">
        Back
    </a>

</form>

</body>
</html>
