'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';
import NextImage from 'next/image';
import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Users, FileText, MessageSquare, CheckSquare, FolderOpen, Image as ImageIcon } from 'lucide-react';
import { userRepository } from '@/features/users/data/user.repository';
import { postRepository } from '@/features/posts/data/post.repository';
import { commentRepository } from '@/features/comments/data/comment.repository';
import { todoRepository } from '@/features/todos/data/todo.repository';
import { albumRepository } from '@/features/albums/data/album.repository';
import { photoRepository } from '@/features/photos/data/photo.repository';

export default function Home() {
  const [stats, setStats] = useState([
    { name: 'Users', icon: Users, count: '0', color: 'bg-blue-500', path: '/users' },
    { name: 'Posts', icon: FileText, count: '0', color: 'bg-green-500', path: '/posts' },
    { name: 'Comments', icon: MessageSquare, count: '0', color: 'bg-purple-500', path: '/comments' },
    { name: 'Todos', icon: CheckSquare, count: '0', color: 'bg-yellow-500', path: '/todos' },
    { name: 'Albums', icon: FolderOpen, count: '0', color: 'bg-pink-500', path: '/albums' },
    { name: 'Photos', icon: ImageIcon, count: '0', color: 'bg-indigo-500', path: '/photos' },
  ]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        setLoading(true);
        const [users, posts, comments, todos, albums, photos] = await Promise.all([
          userRepository.getAll(),
          postRepository.getAll(),
          commentRepository.getAll(),
          todoRepository.getAll(),
          albumRepository.getAll(),
          photoRepository.getAll(),
        ]);

        setStats([
          { name: 'Users', icon: Users, count: users.length.toString(), color: 'bg-blue-500', path: '/users' },
          { name: 'Posts', icon: FileText, count: posts.length.toString(), color: 'bg-green-500', path: '/posts' },
          { name: 'Comments', icon: MessageSquare, count: comments.length.toString(), color: 'bg-purple-500', path: '/comments' },
          { name: 'Todos', icon: CheckSquare, count: todos.length.toString(), color: 'bg-yellow-500', path: '/todos' },
          { name: 'Albums', icon: FolderOpen, count: albums.length.toString(), color: 'bg-pink-500', path: '/albums' },
          { name: 'Photos', icon: ImageIcon, count: photos.length.toString(), color: 'bg-indigo-500', path: '/photos' },
        ]);
      } catch (error) {
        console.error('Failed to fetch stats:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchStats();
  }, []);

  return (
    <DashboardLayout>
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-slate-900">Dashboard</h1>
          <p className="text-slate-500 mt-1">Overview of your resources</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {stats.map((stat) => {
            const Icon = stat.icon;
            return (
              <Link key={stat.name} href={stat.path}>
                <Card className="cursor-pointer hover:shadow-lg transition-shadow">
                  <CardHeader className="flex flex-row items-center justify-between pb-2">
                    <CardTitle className="text-sm font-medium text-slate-600">
                      {stat.name}
                    </CardTitle>
                    <div className={`${stat.color} p-2 rounded-lg`}>
                      <Icon className="h-5 w-5 text-white" />
                    </div>
                  </CardHeader>
                  <CardContent>
                    <div className="text-3xl font-bold text-slate-900">
                      {loading ? '...' : stat.count}
                    </div>
                    <p className="text-xs text-slate-500 mt-1">Total {stat.name.toLowerCase()}</p>
                  </CardContent>
                </Card>
              </Link>
            );
          })}
        </div>

        <Card>
          <CardHeader>
            <CardTitle>Quick Actions</CardTitle>
          </CardHeader>
          <CardContent>
            <p className="text-slate-600">
              Use the sidebar navigation to manage users, posts, comments, todos, albums, and photos.
            </p>
          </CardContent>
        </Card>
      </div>
    </DashboardLayout>
  );
}

export function OldHome() {
  return (
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
      <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start">
        <NextImage
          className="dark:invert"
          src="/next.svg"
          alt="Next.js logo"
          width={180}
          height={38}
          priority
        />
        <ol className="font-mono list-inside list-decimal text-sm/6 text-center sm:text-left">
          <li className="mb-2 tracking-[-.01em]">
            Get started by editing{" "}
            <code className="bg-black/[.05] dark:bg-white/[.06] font-mono font-semibold px-1 py-0.5 rounded">
              app/page.tsx
            </code>
            .
          </li>
          <li className="tracking-[-.01em]">
            Save and see your changes instantly.
          </li>
        </ol>

        <div className="flex gap-4 items-center flex-col sm:flex-row">
          <a
            className="rounded-full border border-solid border-transparent transition-colors flex items-center justify-center bg-foreground text-background gap-2 hover:bg-[#383838] dark:hover:bg-[#ccc] font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 sm:w-auto"
            href="https://vercel.com/new?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
            target="_blank"
            rel="noopener noreferrer"
          >
            <NextImage
              className="dark:invert"
              src="/vercel.svg"
              alt="Vercel logomark"
              width={20}
              height={20}
            />
            Deploy now
          </a>
          <a
            className="rounded-full border border-solid border-black/[.08] dark:border-white/[.145] transition-colors flex items-center justify-center hover:bg-[#f2f2f2] dark:hover:bg-[#1a1a1a] hover:border-transparent font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 w-full sm:w-auto md:w-[158px]"
            href="https://nextjs.org/docs?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
            target="_blank"
            rel="noopener noreferrer"
          >
            Read our docs
          </a>
        </div>
      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <NextImage
            aria-hidden
            src="/file.svg"
            alt="File icon"
            width={16}
            height={16}
          />
          Learn
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <NextImage
            aria-hidden
            src="/window.svg"
            alt="Window icon"
            width={16}
            height={16}
          />
          Examples
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <NextImage
            aria-hidden
            src="/globe.svg"
            alt="Globe icon"
            width={16}
            height={16}
          />
          Go to nextjs.org →
        </a>
      </footer>
    </div>
  );
}
