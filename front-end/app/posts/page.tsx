'use client';

import { useState } from 'react';
import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { usePosts } from '@/features/posts/hooks/usePosts';
import { PostCard, PostForm } from '@/features/posts/components/PostCard';
import { Button } from '@/components/ui/button';
import { Modal } from '@/components/ui/modal';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { Post } from '@/core/types';
import { Plus } from 'lucide-react';

export default function PostsPage() {
  const { posts, loading, error, createPost, updatePost, deletePost } = usePosts();
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [editingPost, setEditingPost] = useState<Post | null>(null);
  const { showToast, ToastComponent } = useToast();

  const handleCreate = async (postData: Omit<Post, 'id'>) => {
    try {
      await createPost(postData);
      setIsCreateModalOpen(false);
      showToast({ message: 'Post created successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to create post', type: 'error' });
    }
  };

  const handleUpdate = async (postData: Partial<Post>) => {
    if (!editingPost) return;
    try {
      await updatePost(editingPost.id, postData);
      setEditingPost(null);
      showToast({ message: 'Post updated successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to update post', type: 'error' });
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deletePost(id);
      showToast({ message: 'Post deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete post', type: 'error' });
    }
  };

  if (loading && posts.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-slate-900">Posts</h1>
            <p className="text-slate-500 mt-1">Manage blog posts</p>
          </div>
          <Button onClick={() => setIsCreateModalOpen(true)}>
            <Plus className="h-4 w-4 mr-2" />
            Add Post
          </Button>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {posts.map((post) => (
            <PostCard
              key={post.id}
              post={post}
              onEdit={setEditingPost}
              onDelete={handleDelete}
            />
          ))}
        </div>

        {posts.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No posts found. Create one to get started.</p>
          </div>
        )}
      </div>

      {/* Create Modal */}
      <Modal
        isOpen={isCreateModalOpen}
        onClose={() => setIsCreateModalOpen(false)}
        title="Create New Post"
        size="lg"
      >
        <PostForm
          onSubmit={handleCreate}
          onCancel={() => setIsCreateModalOpen(false)}
        />
      </Modal>

      {/* Edit Modal */}
      <Modal
        isOpen={!!editingPost}
        onClose={() => setEditingPost(null)}
        title="Edit Post"
        size="lg"
      >
        {editingPost && (
          <PostForm
            post={editingPost}
            onSubmit={handleUpdate}
            onCancel={() => setEditingPost(null)}
          />
        )}
      </Modal>
    </DashboardLayout>
  );
}
