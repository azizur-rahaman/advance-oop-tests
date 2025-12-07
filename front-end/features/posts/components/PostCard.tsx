'use client';

import { useState } from 'react';
import { Post } from '@/core/types';
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Textarea } from '@/components/ui/textarea';
import { Modal } from '@/components/ui/modal';
import { Pencil, Trash2 } from 'lucide-react';

interface PostCardProps {
  post: Post;
  onEdit: (post: Post) => void;
  onDelete: (id: number) => void;
  onClick?: () => void;
}

export function PostCard({ post, onEdit, onDelete, onClick }: PostCardProps) {
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);

  return (
    <>
      <Card className="hover:shadow-md transition-shadow">
        <CardHeader>
          <CardTitle className="text-lg cursor-pointer hover:text-blue-600" onClick={onClick}>
            {post.title}
          </CardTitle>
          <p className="text-xs text-slate-500">User ID: {post.user.id}</p>
        </CardHeader>
        <CardContent>
          <p className="text-sm text-slate-600 line-clamp-3">{post.body}</p>
        </CardContent>
        <CardFooter className="gap-2">
          <Button variant="outline" size="sm" onClick={() => onEdit(post)}>
            <Pencil className="h-4 w-4 mr-1" />
            Edit
          </Button>
          <Button variant="danger" size="sm" onClick={() => setShowDeleteDialog(true)}>
            <Trash2 className="h-4 w-4 mr-1" />
            Delete
          </Button>
        </CardFooter>
      </Card>

      <Modal
        isOpen={showDeleteDialog}
        onClose={() => setShowDeleteDialog(false)}
        title="Delete Post"
        size="sm"
        footer={
          <>
            <Button variant="outline" onClick={() => setShowDeleteDialog(false)}>
              Cancel
            </Button>
            <Button
              variant="danger"
              onClick={() => {
                onDelete(post.id);
                setShowDeleteDialog(false);
              }}
            >
              Delete
            </Button>
          </>
        }
      >
        <p className="text-slate-600">
          Are you sure you want to delete this post? This action cannot be undone.
        </p>
      </Modal>
    </>
  );
}

interface PostFormProps {
  post?: Post;
  userId?: number;
  onSubmit: (post: any) => void;
  onCancel: () => void;
}

export function PostForm({ post, userId, onSubmit, onCancel }: PostFormProps) {
  const [formData, setFormData] = useState({
    title: post?.title || '',
    body: post?.body || '',
    userId: post?.user.id || userId || 1,
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({
      title: formData.title,
      body: formData.body,
      user: { id: formData.userId },
    });
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <label className="block text-sm font-medium text-slate-700 mb-1">User ID</label>
        <Input
          type="number"
          value={formData.userId}
          onChange={(e) => setFormData({ ...formData, userId: parseInt(e.target.value) })}
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-slate-700 mb-1">Title</label>
        <Input
          value={formData.title}
          onChange={(e) => setFormData({ ...formData, title: e.target.value })}
          placeholder="Post title"
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-slate-700 mb-1">Body</label>
        <Textarea
          value={formData.body}
          onChange={(e) => setFormData({ ...formData, body: e.target.value })}
          placeholder="Post content"
          rows={6}
          required
        />
      </div>
      <div className="flex gap-2 justify-end pt-4">
        <Button type="button" variant="outline" onClick={onCancel}>
          Cancel
        </Button>
        <Button type="submit" variant="primary">
          {post ? 'Update' : 'Create'}
        </Button>
      </div>
    </form>
  );
}
