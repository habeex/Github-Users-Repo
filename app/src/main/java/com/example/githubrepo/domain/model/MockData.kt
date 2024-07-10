package com.example.githubrepo.domain.model

object MockData {

    fun getUser(): User{
        return User(
            login = "habeex",
            id = 1849920,
            avatar_url = "https://avatars.githubusercontent.com/u/1849920?v=4",
            url = "https://api.github.com/users/habeex",
            html_url = "https://github.com/habeex",
            repos_url = "https://api.github.com/users/habeex/repos",
            type = "User",
            score = 1.0,
            bio = "",
            location = "Abuja, ",
            followers = 0,
            following = 0,
            name = "Habeeb Bayo",
            blog = "",
            public_repos = 0,
            email = "olorunisholahabeeb@mail.com"
        )
    }

    fun getRepo(): RepositoryModel = RepositoryModel(
        owner = getUser(),
        name = "habeex",
        full_name = "habeex/BluetoothPrinter",
        stargazers_count = 105485,
        forks = 10238,
        watchers = 105485,
        visibility = "public",
        language = "C",
        updated_at = "2024-06-08T09:54:25Z",
        description = "Empowering everyone to build reliable and efficient software.",
        topics = listOf("Design system", "Component- misc", "Status- New")
    );
}