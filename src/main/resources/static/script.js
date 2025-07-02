const apiBase = "/api/blogs";
const blogList = document.getElementById("blogList");
const form = document.getElementById("blogForm");
const toast = document.getElementById("toastMsg"); // ✅ static toast from HTML
const darkToggle = document.getElementById("darkToggle"); // ✅ static toggle from HTML

// 📤 Blog Form Submit
form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("blogId").value;
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const author = document.getElementById("author").value;

    const blog = { title, content, author };
    const url = id ? `${apiBase}/${id}` : apiBase;
    const method = id ? "PUT" : "POST";

    const res = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(blog)
    });

    form.reset();
    fetchBlogs();

    showToast(id ? "Blog updated successfully ✅" : "Blog created successfully 📝");
});

// 📥 Fetch All Blogs
async function fetchBlogs() {
    const res = await fetch(apiBase);
    const blogs = await res.json();

    blogList.innerHTML = "";
    blogs.forEach(blog => {
        const div = document.createElement("div");
        div.className = "blog-card animate";
        div.innerHTML = `
            <h3>${blog.title}</h3>
            <p>${blog.content}</p>
            <small>✍️ ${blog.author} | 🕒 ${new Date(blog.createdAt).toLocaleString()}</small>
            <div class="actions">
                <button onclick="editBlog(${blog.id}, '${blog.title}', '${blog.author}', \`${blog.content}\`)">Edit</button>
                <button class="delete-btn" onclick="deleteBlog(${blog.id})">Delete</button>
            </div>
        `;
        blogList.appendChild(div);
    });
}

// ✏️ Edit Blog
function editBlog(id, title, author, content) {
    document.getElementById("blogId").value = id;
    document.getElementById("title").value = title;
    document.getElementById("author").value = author;
    document.getElementById("content").value = content;
    window.scrollTo({ top: 0, behavior: "smooth" });
}

// 🗑 Delete Blog
async function deleteBlog(id) {
    await fetch(`${apiBase}/${id}`, { method: "DELETE" });
    fetchBlogs();
    showToast("Blog deleted ❌");
}

// ✅ Toast Function (single element)
function showToast(message) {
    if (!toast) return;
    toast.textContent = message;
    toast.classList.add("show");

    clearTimeout(toast.hideTimeout);
    toast.hideTimeout = setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

// 🌙 Dark Mode Toggle
darkToggle.addEventListener("click", () => {
    document.body.classList.toggle("dark");
    darkToggle.textContent = document.body.classList.contains("dark") ? "☀️" : "🌙";
});

fetchBlogs();
